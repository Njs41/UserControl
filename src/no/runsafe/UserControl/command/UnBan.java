package no.runsafe.UserControl.command;

import no.runsafe.UserControl.database.PlayerDatabase;
import no.runsafe.framework.command.ExecutableCommand;
import no.runsafe.framework.server.ICommandExecutor;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class UnBan extends ExecutableCommand
{
	public UnBan(PlayerDatabase playerDatabase)
	{
		super("unban", "Unbans a player from the server", "runsafe.usercontrol.unban", "player", "reason");
		playerdb = playerDatabase;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters, String[] arguments)
	{
		RunsafePlayer player = RunsafeServer.Instance.getPlayer(parameters.get("player"));
		if (!player.isBanned())
			return String.format("Player %s is not banned.", player.getPrettyName());

		// TODO Log unbanning reason
		playerdb.logPlayerUnban(player);
		player.setBanned(false);
		return String.format("Player %s was unbanned.", player.getPrettyName());
	}

	private final PlayerDatabase playerdb;
}
