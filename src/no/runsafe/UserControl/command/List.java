package no.runsafe.UserControl.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.RunsafeServer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Map;

public class List extends ExecutableCommand
{
	public List()
	{
		super("list", "List players connected to the server", "runsafe.usercontrol.list");
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		ArrayList<String> online = new ArrayList<String>();
		for (IPlayer player : RunsafeServer.Instance.getOnlinePlayers())
		{
			if (executor instanceof IPlayer && ((IPlayer) executor).shouldNotSee(player))
				continue;
			online.add(player.getPrettyName());
		}

		return String.format(
			"There are %d/%d players connected:\n %s",
			online.size(),
			RunsafeServer.Instance.getMaxPlayers(),
			StringUtils.join(online, ", ")
		);
	}
}