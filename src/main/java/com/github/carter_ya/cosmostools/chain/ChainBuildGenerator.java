package com.github.carter_ya.cosmostools.chain;

import com.github.carter_ya.cosmostools.AbstractGenerator;
import com.intellij.execution.configurations.GeneralCommandLine;

/**
 * @author Carter
 */
public class ChainBuildGenerator extends AbstractGenerator {

    private boolean verboseOutput = false;

    public ChainBuildGenerator(String workDir) {
        super(workDir);
    }

    @Override
    protected GeneralCommandLine generate0() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.withParameters("chain", "build");
        if (verboseOutput) {
            commandLine.withParameters("--verbose");
        }
        return commandLine;
    }

    public boolean isVerboseOutput() {
        return verboseOutput;
    }

    public void setVerboseOutput(boolean verboseOutput) {
        this.verboseOutput = verboseOutput;
    }
}
