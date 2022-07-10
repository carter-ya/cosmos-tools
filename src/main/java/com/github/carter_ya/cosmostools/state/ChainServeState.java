package com.github.carter_ya.cosmostools.state;

import com.github.carter_ya.cosmostools.Constants;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Carter
 */
@State(
    name = "ChainServeState",
    storages = {
        @Storage(
            value = Constants.PROJECT_STATE_NAME
        )
    },
    defaultStateAsResource = true
)
public class ChainServeState implements PersistentStateComponent<ChainServeState> {

    // Force reset of the app state on start and every source change
    private boolean forceReset = false;
    // Reset of the app state on first start
    private boolean resetOnce = false;
    // Verbose output
    private boolean verboseOutput = false;
    // Ignite config file
    private String configFile = "./config.yml";

    @Override
    public @Nullable ChainServeState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ChainServeState state) {
        XmlSerializerUtil.copyBean(state, this);
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
