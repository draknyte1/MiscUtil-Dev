package miscutil.core.xmod.growthcraft;

import miscutil.core.lib.LoadedMods;
import miscutil.core.xmod.growthcraft.booze.Register_Booze;

public class HANDLER_Growthcraft {
	

	  //Run me during Pre-Init
	public static void preInit(){
		if (LoadedMods.Growthcraft){
			Register_Booze.preInit();			
		}
	}
	
}