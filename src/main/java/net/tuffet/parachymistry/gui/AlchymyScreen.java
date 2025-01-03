package net.tuffet.parachymistry.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

@Environment(EnvType.CLIENT)
public class AlchymyScreen extends ForgingScreen<AlchymyScreenHandler> {
    private static final Identifier ERROR_TEXTURE = Identifier.ofVanilla("container/smithing/error");

    public AlchymyScreen(AlchymyScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, Parachymistry.id("textures/gui/sprites/alchemical_table.png"));
        this.titleX = 40;
        this.titleY = 5;
    }

    @Override
    public AlchymyScreenHandler getScreenHandler() {
        this.playerInventoryTitleY = this.backgroundHeight - 98;
        return super.getScreenHandler();
    }

    @Override
    protected void setup() {
        assert this.client != null;
        assert this.client.world != null;
    }

    @Override
    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if (this.hasInvalidRecipe()) {
            context.drawGuiTexture(ERROR_TEXTURE, x + 65, y + 46, 28, 21);
        }
    }

    private boolean hasInvalidRecipe() {
        return this.handler.getSlot(0).hasStack() && this.handler.getSlot(1).hasStack() && this.handler.getSlot(2).hasStack() && !this.handler.getSlot(this.handler.getResultSlotIndex()).hasStack();
    }
}

