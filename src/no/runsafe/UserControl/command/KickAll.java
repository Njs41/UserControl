package no.runsafe.UserControl.command;

import no.runsafe.UserControl.database.PlayerKickLog;
import no.runsafe.framework.command.RunsafeCommand;
import no.runsafe.framework.configuration.IConfiguration;
import no.runsafe.framework.event.IConfigurationChanged;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafePlayer;
import org.apache.commons.lang.StringUtils;

public class KickAll extends RunsafeCommand implements IConfigurationChanged
{
	public KickAll(PlayerKickLog log)
	{
		super("kickall", "reason");
		logger = log;
	}

	@Override
	public String requiredPermission()
	{
		return "runsafe.usercontrol.kickall";
	}

	@Override
	public String OnExecute(RunsafePlayer executor, String[] args)
	{
		String source = executor == null ? "console" : executor.getName();
		String reason = StringUtils.join(args, " ");
		int n = 0;

		for (RunsafePlayer victim : RunsafeServer.Instance.getOnlinePlayers())
			if (executor == null || (executor.canSee(victim) && !victim.getName().equals(executor.getName())))
			{
				if (!victim.hasPermission("runsafe.usercontrol.kick.immune"))
				{
					victim.kick(String.format(kickMessageFormat, source, reason));
					logger.logKick(executor, victim, reason, false);
					n++;
				}
			}
		return String.format("Kicked %d players from the server.", n);
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		kickMessageFormat = configuration.getConfigValueAsString("messages.kick");
	}

	String kickMessageFormat = "%2$s";
	PlayerKickLog logger;
}
