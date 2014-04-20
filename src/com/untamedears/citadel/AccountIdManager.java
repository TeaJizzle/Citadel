package com.untamedears.citadel;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.untamedears.citadel.dao.CitadelDao;
 
public class AccountIdManager implements Listener {
  public AccountIdManager() {}

  public void initialize(CitadelDao dao) {
    dao_ = dao;
    accountIdCache_ = dao_.loadAccountIdMap();
  }

  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent event) {
    final Player player = event.getPlayer();
    final String playerName = player.getName();
    final UUID accountId = player.getUniqueId();
    final String associatedName = accountIdCache_.get(accountId);
    if (associatedName != null && associatedName.equalsIgnoreCase(playerName)) {
      return;
    }
    dao_.associatePlayerAccount(accountId, playerName);
    accountIdCache_.put(accountId, playerName);
  }

  public String getPlayerName(UUID accountId) {
    return accountIdCache_.get(accountId);
  }

  public Player getPlayer(UUID accountId) {
    final String playerName = getPlayerName(accountId);
    if (playerName == null) {
      return null;
    }
    return Bukkit.getPlayerExact(playerName);
  }

  CitadelDao dao_;
  private Map<UUID, String> accountIdCache_;
}
