package withicality.handswitchmodupdated.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.util.Arm;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Key;

@Environment(EnvType.CLIENT)
public class HandSwitchModUpdatedClient implements ClientModInitializer {
    public final KeyBinding kb;

    public HandSwitchModUpdatedClient() {
        this.kb = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.hand-switch-mod.hand-switch", InputUtil.UNKNOWN_KEY.getCode(), "key.category.hand-switch"));
    }

    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (kb.wasPressed()) {
                client.options.getMainArm().setValue(client.options.getMainArm().getValue() == Arm.LEFT ? Arm.RIGHT : Arm.LEFT);
                client.options.write();
            }
        });
    }
}
