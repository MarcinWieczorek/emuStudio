/*
 * KISS, YAGNI, DRY
 *
 * (c) Copyright 2006-2017, Peter Jakubčo
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package net.sf.emustudio.ssem.assembler;

import emulib.annotations.PLUGIN_TYPE;
import emulib.annotations.PluginType;
import emulib.plugins.compiler.AbstractCompiler;
import emulib.plugins.compiler.LexicalAnalyzer;
import emulib.plugins.compiler.SourceFileExtension;
import emulib.plugins.memory.MemoryContext;
import emulib.runtime.ContextPool;
import emulib.runtime.exceptions.ContextNotFoundException;
import emulib.runtime.exceptions.InvalidContextException;
import java_cup.runtime.ComplexSymbolFactory;
import net.sf.emustudio.ssem.assembler.tree.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.Reader;
import java.util.Objects;

@PluginType(
    type = PLUGIN_TYPE.COMPILER,
    title = "SSEM Assembler",
    copyright = "\u00A9 Copyright 2016-2017, Peter Jakubčo",
    description = "Assembler of SSEM processor language"
)
@SuppressWarnings("unused")
public class CompilerImpl extends AbstractCompiler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompilerImpl.class);
    private static final SourceFileExtension[] SOURCE_FILE_EXTENSIONS = new SourceFileExtension[]{
        new SourceFileExtension("ssem", "SSEM source file")
    };
    private final ContextPool contextPool;

    public CompilerImpl(Long pluginID, ContextPool contextPool) {
        super(pluginID);
        this.contextPool = Objects.requireNonNull(contextPool);
    }

    @Override
    public boolean compile(String inputFileName, String outputFileName) {
        notifyCompileStart();
        notifyInfo(getTitle() + ", version " + getVersion());

        MemoryContext<Byte> memory = null;
        try {
            memory = contextPool.getMemoryContext(pluginID, MemoryContext.class);
        } catch (InvalidContextException | ContextNotFoundException e) {
            notifyWarning("Memory is not available");
        }

        int errorCode = 0;
        try (Reader reader = new FileReader(inputFileName)) {
            try (CodeGenerator codeGenerator = new CodeGenerator(new MemoryAndFileOutput(outputFileName, memory))) {
                LexerImpl lexer = new LexerImpl(reader);
                ParserImpl parser = new ParserImpl(lexer, new ComplexSymbolFactory(), this);

                Program program = (Program) parser.parse().value;
                if (program == null) {
                    throw new Exception("Unexpected end of file");
                }
                if (parser.hasSyntaxErrors()) {
                    throw new Exception("One or more errors has been found, cannot continue.");
                }

                program.accept(codeGenerator);
                programStart = program.getStartLine() * 4;
                notifyInfo("Compile was successful. Output: " + outputFileName);
            }
        } catch (Exception e) {
            errorCode = 1;
            LOGGER.error("Compilation error.", e);
            notifyError("Compilation error: " + e.getMessage());

            return false;
        } finally {
            notifyCompileFinish(errorCode);
        }

        return true;
    }

    @Override
    public boolean compile(String inputFileName) {
        String outputFileName = Objects.requireNonNull(inputFileName);
        SourceFileExtension srcExtension = SOURCE_FILE_EXTENSIONS[0];

        int i = inputFileName.lastIndexOf("." + srcExtension.getExtension());
        if (i >= 0) {
            outputFileName = outputFileName.substring(0, i);
        }
        return compile(inputFileName, outputFileName + ".bin");
    }

    @Override
    public LexicalAnalyzer getLexer(Reader reader) {
        return new LexerImpl(reader);
    }

    @Override
    public SourceFileExtension[] getSourceSuffixList() {
        return SOURCE_FILE_EXTENSIONS;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void showSettings() {

    }

    @Override
    public boolean isShowSettingsSupported() {
        return false;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
