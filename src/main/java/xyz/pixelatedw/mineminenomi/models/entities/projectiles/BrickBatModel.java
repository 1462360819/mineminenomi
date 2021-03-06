package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BrickBatModel extends EntityModel
{
	public RendererModel body;
	public RendererModel body2;
	public RendererModel body3;
	public RendererModel body4;
	public RendererModel rightear;
	public RendererModel leftear;
	public RendererModel rightWing1;
	public RendererModel leftWing1;
	public RendererModel rightWing2;
	public RendererModel leftWing2;

	public BrickBatModel()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.body3 = new RendererModel(this, 42, 0);
		this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body3.addBox(-2.0F, -2.0F, -3.0F, 4, 4, 6, 0.0F);
		this.leftWing1 = new RendererModel(this, 80, 5);
		this.leftWing1.setRotationPoint(1.0F, 0.0F, -1.0F);
		this.leftWing1.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);
		this.setRotateAngle(leftWing1, 0.8726646259971648F, -0.0F, -0.4363323129985824F);
		this.rightWing1 = new RendererModel(this, 80, 0);
		this.rightWing1.setRotationPoint(-1.0F, 0.0F, -1.0F);
		this.rightWing1.addBox(-6.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);
		this.setRotateAngle(rightWing1, 0.8726646259971648F, -0.0F, 0.4363323129985824F);
		this.rightWing2 = new RendererModel(this, 93, 0);
		this.rightWing2.setRotationPoint(-6.0F, 0.0F, 0.0F);
		this.rightWing2.addBox(-5.0F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
		this.setRotateAngle(rightWing2, 0.0F, -0.5235987755982988F, -0.17453292519943295F);
		this.body2 = new RendererModel(this, 21, 0);
		this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body2.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 4, 0.0F);
		this.body4 = new RendererModel(this, 63, 0);
		this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body4.addBox(-2.0F, -3.0F, -2.0F, 4, 6, 4, 0.0F);
		this.rightear = new RendererModel(this, 0, 11);
		this.rightear.setRotationPoint(-1.9F, -2.6F, 0.0F);
		this.rightear.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(rightear, 0.0F, -0.0F, -0.5235987755982988F);
		this.leftear = new RendererModel(this, 0, 11);
		this.leftear.setRotationPoint(1.9F, -2.6F, 0.0F);
		this.leftear.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(leftear, 0.0F, -0.0F, 0.5235987755982988F);
		this.body = new RendererModel(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
		this.leftWing2 = new RendererModel(this, 93, 5);
		this.leftWing2.setRotationPoint(6.0F, 0.0F, 0.0F);
		this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 5, 4, 0, 0.0F);
		this.setRotateAngle(leftWing2, 0.0F, 0.5235987755982988F, 0.0F);
		this.body.addChild(this.body3);
		this.body.addChild(this.leftWing1);
		this.body.addChild(this.rightWing1);
		this.rightWing1.addChild(this.rightWing2);
		this.body.addChild(this.body2);
		this.body.addChild(this.body4);
		this.body.addChild(this.rightear);
		this.body.addChild(this.leftear);
		this.leftWing1.addChild(this.leftWing2);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
	{
		double[] animationWingMovement = new double[]
			{
					25, 30, 35, 40, 45, 50, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0, -5, -10, -15, -10, -5, 0, 5, 10, 15, 20, 25
			};

		int cycleIndexFly = (int) ((ent.ticksExisted * 1.75) % animationWingMovement.length);

		if (!Minecraft.getInstance().isGamePaused())
		{
			this.rightWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]);
			this.leftWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]) * -1;
		}
	}

	protected float degToRad(double degrees)
	{
		return (float) (degrees * Math.PI / 180);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.body.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
