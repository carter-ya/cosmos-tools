package com.github.carter_ya.cosmostools.gui;

import com.github.carter_ya.cosmostools.Constants;
import com.github.carter_ya.cosmostools.action.ProjectActivateAction;
import com.github.carter_ya.cosmostools.chain.ChainServeGenerator;
import com.github.carter_ya.cosmostools.components.ChainServeForm;
import com.github.carter_ya.cosmostools.state.ChainServeState;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBScrollPane;
import javax.swing.Action;
import javax.swing.JComponent;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Carter
 */
public class ChainServeDialog extends DialogWrapper {

    private final Project project;
    private final ChainServeForm form;

    public ChainServeDialog(@NotNull Project project) {
        super(project);
        this.project = project;
        this.form = new ChainServeForm();
        init();
        setTitle("Chain Serve");

        // set button text
        setOKButtonText("Chain Serve");
        setCancelButtonText("Cancel");

        // init state
        ChainServeState state = ServiceManager.getService(project, ChainServeState.class);
        form.setDate(state);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new JBScrollPane(form.getRootComponent());
    }

    @Override
    protected Action @NotNull [] createActions() {
        return new Action[]{
            getOKAction(),
            getCancelAction(),
        };
    }

    @Override
    protected @Nullable ValidationInfo doValidate() {
        String configFile = form.getConfigFileTextField().getText().trim();
        if (configFile.isEmpty()) {
            return new ValidationInfo("Config file is empty", form.getConfigFileTextField());
        }
        return super.doValidate();
    }

    @Override
    protected void doOKAction() {
        ChainServeState state = ServiceManager.getService(project, ChainServeState.class);
        form.getDate(state);

        VirtualFile projectDir = ProjectUtil.guessProjectDir(project);
        if (projectDir == null) {
            Messages.showErrorDialog(project, "Can't guess project dir!", Constants.PLUGIN_NAME);
            return;
        }
        ChainServeGenerator generator = new ChainServeGenerator(projectDir.getCanonicalPath());
        generator.setForceReset(state.isForceReset());
        generator.setResetOnce(state.isResetOnce());
        generator.setVerboseOutput(state.isVerboseOutput());
        generator.setConfigFile(state.getConfigFile());

        dispose();
        ProjectActivateAction.restarter(generator, project);
    }

    @Override
    protected @Nullable @NonNls String getDimensionServiceKey() {
        return Constants.NAME + ":" + getClass().getName();
    }
}
