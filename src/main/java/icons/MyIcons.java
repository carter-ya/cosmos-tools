package icons;

import com.intellij.ide.presentation.Presentation;
import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;

/**
 * @author Carter
 */
@Presentation
public interface MyIcons {

    Icon Cosmos = IconLoader.getIcon("/icons/cosmos-logo-vertical-light.svg",
        MyIcons.class);

}
