package no.runsafe.UserControl.command;

import no.runsafe.UserControl.LoginRedirectManager;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

import java.util.Map;

public class SetRedirectLocation extends PlayerCommand
{
	public SetRedirectLocation(LoginRedirectManager loginRedirectManager)
	{
		super("setredirectlocation", "Sets a server-wide re-direction location for when players log-in", "runsafe.usercontrol.setredirectlocation");
		this.loginRedirectManager = loginRedirectManager;
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		this.loginRedirectManager.setRedirectLocation(executor.getLocation());
		executor.sendMessage("Re-direction location set to your location.");
		return null;
	}

	private final LoginRedirectManager loginRedirectManager;
}
