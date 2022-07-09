package com.github.carter_ya.cosmostools.proto;

import com.github.carter_ya.cosmostools.AbstractGenerator;
import com.intellij.execution.configurations.GeneralCommandLine;

/**
 * @author Carter
 */
public class ProtoGenerator extends AbstractGenerator {

    private boolean generateGo = false;
    private boolean generateOpenAPI = false;

    public ProtoGenerator(String workDir) {
        super(workDir);
    }

    @Override
    public GeneralCommandLine generate0() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        if (generateGo && generateOpenAPI) {
            commandLine.withParameters("generate", "proto-go", "-y")
                .withParameters("&&", getExecutor(), "generate", "openapi", "-y");
        } else if (generateGo) {
            commandLine.withParameters("generate", "proto-go", "-y");
        } else {
            commandLine.withParameters("generate", "openapi", "-y");
        }
        return commandLine;
    }

    public boolean isGenerateGo() {
        return generateGo;
    }

    public void setGenerateGo(boolean generateGo) {
        this.generateGo = generateGo;
    }

    public boolean isGenerateOpenAPI() {
        return generateOpenAPI;
    }

    public void setGenerateOpenAPI(boolean generateOpenAPI) {
        this.generateOpenAPI = generateOpenAPI;
    }
}
