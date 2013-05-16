package com.untamedears.citadel.command;

import org.bukkit.command.CommandSender;

import com.untamedears.citadel.Citadel;
import com.untamedears.citadel.groups.GroupMediator;
import com.untamedears.citadel.manager.ConfigManager;
import com.untamedears.citadel.manager.PlayerManager;
import com.untamedears.citadel.manager.ReinforcementManager;

public abstract class PlayerCommand implements Command {
	
	protected ConfigManager configManager = Citadel.getConfigManager();
	protected PlayerManager playerManager = Citadel.getPlayerManager();
	protected GroupMediator groupMediator = Citadel.getGroupMediator();
	protected ReinforcementManager reinforcementManager = Citadel.getReinforcementManager();
	
	private final String name;
	private String description = "";
	private String usage = "";
	private int minArguments = 0;
	private int maxArguments = 0;
	private String[] identifiers = new String[0];
	
	public PlayerCommand(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getUsage(){
		return this.usage;
	}
	
	public int getMinArguments(){
		return this.minArguments;
	}
	
	public int getMaxArguments(){
		return this.maxArguments;
	}
	
	public String[] getIdentifiers(){
		return this.identifiers;
	}
	
	public boolean isIdentifier(CommandSender executor, String input){
		for(String identifier : this.identifiers){
			if(input.equalsIgnoreCase(identifier)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isInProgress(CommandSender sender){
		return false;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setUsage(String usage){
		this.usage = usage;
	}
	
	public void setArgumentRange(int min, int max){
		this.minArguments = min;
		this.maxArguments = max;
	}
	
	public void setIdentifiers(String[] identifiers){
		this.identifiers = identifiers;
	}
	
}