package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;

@OnlyIn(Dist.CLIENT)
public class DoppelmanRenderer extends GenericMobRenderer<DoppelmanEntity, BipedModel<DoppelmanEntity>>
{

	public DoppelmanRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new SimpleHumanModel(), "doppelman");
	}
	
	@Override
	protected void preRenderCallback(DoppelmanEntity entity, float f)
	{
		float scale = 1 + ((float)entity.getShadows() / 7);

		if(scale < 1)
			scale = 1;
		
		GL11.glScalef(scale, scale, scale);
	}
	
	public static class Factory implements IRenderFactory<DoppelmanEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super DoppelmanEntity> createRenderFor(EntityRendererManager manager)
		{
			return new DoppelmanRenderer(manager);
		}
	}
}
