package com.github.carter_ya.cosmostools.components;

import com.github.carter_ya.cosmostools.state.ChainServeState;
import com.intellij.ui.components.fields.ExpandableTextField;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Carter
 */
public class ChainServeForm {

    private JCheckBox forceResetCheckBox;
    private JCheckBox resetOnceCheckBox;
    private JCheckBox verboseOutputCheckBox;
    private JPanel rootComponent;
    private ExpandableTextField configFileTextField;

    public void setDate(ChainServeState state) {
        forceResetCheckBox.setSelected(state.isForceReset());
        resetOnceCheckBox.setSelected(state.isResetOnce());
        verboseOutputCheckBox.setSelected(state.isVerboseOutput());
        configFileTextField.setText(StringUtils.trimToEmpty(state.getConfigFile()));
    }

    public void getDate(ChainServeState state) {
        state.setForceReset(forceResetCheckBox.isSelected());
        state.setResetOnce(resetOnceCheckBox.isSelected());
        state.setVerboseOutput(verboseOutputCheckBox.isSelected());
        state.setConfigFile(StringUtils.trimToEmpty(configFileTextField.getText()));
    }

    public JCheckBox getForceResetCheckBox() {
        return forceResetCheckBox;
    }

    public void setForceResetCheckBox(JCheckBox forceResetCheckBox) {
        this.forceResetCheckBox = forceResetCheckBox;
    }

    public JCheckBox getResetOnceCheckBox() {
        return resetOnceCheckBox;
    }

    public void setResetOnceCheckBox(JCheckBox resetOnceCheckBox) {
        this.resetOnceCheckBox = resetOnceCheckBox;
    }

    public JCheckBox getVerboseOutputCheckBox() {
        return verboseOutputCheckBox;
    }

    public void setVerboseOutputCheckBox(JCheckBox verboseOutputCheckBox) {
        this.verboseOutputCheckBox = verboseOutputCheckBox;
    }

    public ExpandableTextField getConfigFileTextField() {
        return configFileTextField;
    }

    public void setConfigFileTextField(
        ExpandableTextField configFileTextField) {
        this.configFileTextField = configFileTextField;
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(JPanel rootComponent) {
        this.rootComponent = rootComponent;
    }
}
