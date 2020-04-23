package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;
import xyz.pixelatedw.wypi.APIConfig;

public class WaxPlayerRenderer extends GenericMobRenderer<WaxPlayerEntity, BipedModel<WaxPlayerEntity>> {

	public static final ResourceLocation WAX_LOCATION = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/wax_player.png");

	public WaxPlayerRenderer(EntityRendererManager manager)
	{
		super(manager, new SimpleHumanModel());
	}
		
	@Override
	protected ResourceLocation getEntityTexture(WaxPlayerEntity entity) 
	{
		if(entity.getTextureId() == null) {
			return WAX_LOCATION;
		} else {
        ClientWorld world = (ClientWorld) entity.world;
        ClientPlayerEntity p =	(ClientPlayerEntity) world.getPlayerByUuid(entity.getTextureId().get());
        
		return p.getLocationSkin();
		}
	}
	
	public static class Factory implements IRenderFactory<WaxPlayerEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super WaxPlayerEntity> createRenderFor(EntityRendererManager manager)
		{
			return new WaxPlayerRenderer(manager);
		}
	}
}