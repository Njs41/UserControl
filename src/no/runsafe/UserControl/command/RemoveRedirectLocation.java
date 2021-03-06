package no.runsafe.UserControl.command;

import no.runsafe.UserControl.LoginRedirectManager;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class RemoveRedirectLocation extends PlayerCommand
{
	public RemoveRedirectLocation(LoginRedirectManager loginRedirectManager)
	{
		super("removeredirectlocation", "Removes the re-direction location", "runsafe.usercontrol.removeredirectlocation");
		this.loginRedirectManager = loginRedirectManager;
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		this.loginRedirectManager.removeRedirectLocation();
		executor.sendMessage("Re-direction location has been removed.");
		return null;
	}

	private final LoginRedirectManager loginRedirectManager;
}
