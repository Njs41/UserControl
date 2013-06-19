package no.runsafe.UserControl;

import no.runsafe.UserControl.command.*;
import no.runsafe.UserControl.database.PlayerDatabase;
import no.runsafe.UserControl.database.PlayerKickLog;
import no.runsafe.UserControl.database.PlayerSessionLog;
import no.runsafe.UserControl.events.KickEvent;
import no.runsafe.UserControl.events.Login;
import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.api.hook.IPlayerPermissions;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{
		// Components
		addComponent(PlayerHandler.class);
		addComponent(PlayerDatabase.class);
		addComponent(PlayerSessionLog.class);
		addComponent(PlayerKickLog.class);
		addComponent(SessionLogger.class);
		addComponent(OpController.class);
		addComponent(LoginRedirectManager.class);
		addComponent(MaintenanceHandler.class);

		addComponent(getFirstPluginAPI(IPlayerPermissions.class));

		// Commands
		addComponent(Ban.class);
		addComponent(BanIP.class);
		addComponent(Kick.class);
		addComponent(KickAll.class);
		addComponent(SuDo.class);
		addComponent(TempBan.class);
		addComponent(UnBan.class);
		addComponent(UnBanIP.class);
		addComponent(BanEnforcer.class);
		addComponent(Seen.class);
		addComponent(Whois.class);
		addComponent(Op.class);
		addComponent(DeOp.class);
		addComponent(Played.class);
		addComponent(SetFirstSpawn.class);
		addComponent(SetRedirectLocation.class);
		addComponent(RemoveRedirectLocation.class);
		addComponent(Maintenance.class);
		addComponent(Rank.class);

		// Events
		addComponent(KickEvent.class);
		addComponent(Login.class);
	}
}
