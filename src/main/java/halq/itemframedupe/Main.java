package halq.itemframedupe;

import com.github.folia.api.FoliaPlayer;
import com.github.folia.api.entity.FoliaItemFrame;
import com.github.folia.api.event.entity.FoliaEntityDamageByEntityEvent;
import com.github.folia.api.item.FoliaItemStack;
import com.github.folia.api.plugin.FoliaPlugin;
import com.github.folia.api.world.FoliaLocation;

public class Main extends FoliaPlugin implements FoliaListener {

    private int probability;

    @Override
    public void onEnable() {
        this.probability = getConfig().getInt("probability", 100);
        registerEvents(this);
    }

    @SubscribeEvent
    public void onFrameDamage(FoliaEntityDamageByEntityEvent event) {
        FoliaItemFrame frame = (FoliaItemFrame) event.getEntity();

        if (!frame.isEmpty() && Math.random() * 100 <= probability) {
            FoliaItemStack item = frame.getItem();
            FoliaLocation location = frame.getLocation().add(0, 1, 0);
            frame.getWorld().dropItem(location, item);
            frame.setVelocity(0, 0, 0);
        }
    }
}


