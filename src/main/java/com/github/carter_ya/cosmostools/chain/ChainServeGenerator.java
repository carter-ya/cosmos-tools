package com.github.carter_ya.cosmostools.chain;

import com.github.carter_ya.cosmostools.AbstractGenerator;
import com.intellij.execution.configurations.GeneralCommandLine;

/**
 * @author Carter
 */
public class ChainServeGenerator extends AbstractGenerator {

    // Force reset of the app state on start and every source change
    private boolean forceReset = false;
    // Reset of the app state on first start
    private boolean resetOnce = false;
    // Verbose output
    private boolean verboseOutput = false;
    // Ignite config file
    private String configFile = "./config.yml";

    public ChainServeGenerator(String workDir) {
        super(workDir);
    }

    @Override
    protected GeneralCommandLine generate0() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.withParameters("chain", "serve", "--config", configFile);
        if (forceReset) {
            commandLine.withParameters("--force-reset");
        }
        if (resetOnce) {
            commandLine.withParameters("--reset-once");
        }
        if (verboseOutput) {
            commandLine.withParameters("--verbose");
        }
        return commandLine;
    }

    public boolean isForceReset() {
        return forceReset;
    }

    public void setForceReset(boolean forceReset) {
        this.forceReset = forceReset;
    }

    public boolean isResetOnce() {
        return resetOnce;
    }

    public void setResetOnce(boolean resetOnce) {
        this.resetOnce = resetOnce;
    }

    public boolean isVerboseOutput() {
        return verboseOutput;
    }

    public void setVerboseOutput(boolean verboseOutput) {
        this.verboseOutput = verboseOutput;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
