package com.github.carter_ya.cosmostools;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.GeneralCommandLine.ParentEnvironmentType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Carter
 */
public abstract class AbstractGenerator {

    protected final String workDir;
    private String executor = "ignite";

    protected AbstractGenerator(String workDir) {
        this.workDir = workDir;
    }

    protected Runtime getRuntime() {
        return Runtime.getRuntime();
    }

    protected Process exec(List<String> commands) throws IOException {
        Runtime runtime = getRuntime();
        System.getenv("os");
        List<String> commandList = new ArrayList<>();
        commandList.addAll(Arrays.asList(getCmd()));
        commandList.addAll(commands);

        return runtime.exec(commandList.toArray(new String[0]), null, new File(workDir));
    }

    protected String[] getCmd() {
        if (SystemInfo.isWindows) {
            return new String[]{"cmd.exe", "/c"};
        } else {
            return new String[]{"/bin/sh", "-c"};
        }
    }

    public GeneralCommandLine generate() {
        GeneralCommandLine commandLine = generate0();
        commandLine.withParentEnvironmentType(ParentEnvironmentType.CONSOLE);
        commandLine.setExePath(executor);
        commandLine.setWorkDirectory(workDir);
        return commandLine;
    }

    protected abstract GeneralCommandLine generate0();

    protected String concatCommand(String... args) {
        var argList = new ArrayList<String>();
        argList.add(executor);
        argList.addAll(Arrays.asList(args));
        return String.join(" ", argList.toArray(new String[0]));
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
