package com.github.carter_ya.cosmostools.action;

import com.github.carter_ya.cosmostools.AbstractGenerator;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.actions.StopProcessAction;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.filters.UrlFilter;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.util.io.BaseOutputReader;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

/**
 * @author Carter
 */
public abstract class ProjectActivateAction extends AnAction {

    protected void restarter(AbstractGenerator generator, Project project) {
        GeneralCommandLine commandLine = generator.generate();

        ColoredProcessHandler processHandler;
        try {
            processHandler = new ColoredProcessHandler(commandLine) {
                @Override
                protected @NotNull BaseOutputReader.Options readerOptions() {
                    return BaseOutputReader.Options.forMostlySilentProcess();
                }

                @Override
                public boolean isSilentlyDestroyOnClose() {
                    return true;
                }
            };
        } catch (ExecutionException ex) {
            throw new RuntimeException(ex);
        }
        ConsoleView consoleView = TextConsoleBuilderFactory.getInstance()
            .createBuilder(project)
            .filters(new UrlFilter(project))
            .getConsole();

        // must call getComponent before createConsoleActions()
        JComponent consoleViewComponent = consoleView.getComponent();

        RunContentDescriptor descriptor = new RunContentDescriptor(
            consoleView,
            processHandler,
            new JPanel(new BorderLayout()),
            "Cosmos Tools"
        );
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        actionGroup.add(new RerunAction(() -> restarter(generator, project)));
        actionGroup.add(new StopProcessAction("Stop", "Stop", processHandler));
        actionGroup.addSeparator();
        actionGroup.addAll(consoleView.createConsoleActions());

        ActionToolbar toolbar = ActionManager.getInstance()
            .createActionToolbar("Cosmos Tools", actionGroup, false);
        toolbar.setTargetComponent(consoleViewComponent);

        JComponent ui = descriptor.getComponent();
        ui.add(consoleViewComponent, BorderLayout.CENTER);
        ui.add(toolbar.getComponent(), BorderLayout.WEST);
        processHandler.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(@NotNull ProcessEvent event) {

            }
        });
        consoleView.attachToProcess(processHandler);
        processHandler.startNotify();

        RunContentManager.getInstance(project).showRunContent(
            DefaultRunExecutor.getRunExecutorInstance(),
            descriptor
        );
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        Project project = e.getProject();
        e.getPresentation().setEnabled(project != null);
    }
}
