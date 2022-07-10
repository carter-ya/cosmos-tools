package com.github.carter_ya.cosmostools;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.GeneralCommandLine.ParentEnvironmentType;

/**
 * @author Carter
 */
public abstract class AbstractGenerator {

    protected final String workDir;
    private String executor = "ignite";

    protected AbstractGenerator(String workDir) {
        this.workDir = workDir;
    }

    public GeneralCommandLine generate() {
        GeneralCommandLine commandLine = generate0();
        commandLine.withParentEnvironmentType(ParentEnvironmentType.CONSOLE);
        commandLine.setExePath(executor);
        commandLine.setWorkDirectory(workDir);
        return commandLine;
    }

    protected abstract GeneralCommandLine generate0();

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
