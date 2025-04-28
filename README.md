# AuthMeBungee
[![Build Status](https://github.com/WaveHDMI/AuthMeBungee/actions/workflows/gradle.yml/badge.svg)](https://github.com/WaveHDMI/AuthMeBungee/actions?query=branch%3Amaster)

This plugin acts like a bridge between your bukkit servers and your BungeeCord instance. To explain simple how it works, bukkit-side plugins send a message to bungee-side on user authentication. If bungee-side doesn't receive this message, the player won't be able to talk in chat and to perform commands, including BungeeCord commands.

**AutoLogin:** If you have AuthMe on multiple servers, you can enable autoLogin option that allow you to switch between your servers without having to perform login command again.

## Support
- [GitHub issue tracker](https://github.com/WaveHDMI/AuthMeBungee/issues)
- [Official Spigot page](https://www.spigotmc.org/resources/authmebungee.50219/)
- [Official AuthMe Discord](https://discord.gg/Vn9eCyE)

## Requirements

- Java 1.8+
- BungeeCord/Waterfall/Travertine 1.7+
- CraftBukkit/Spigot/Paper/Tacospigot 1.7.10+

## Installation

1. Download AuthMeBungee package
2. Place AuthMeBungee.jar into your BungeeCord's plugin folder
4. Restart everything
5. Configure the plugin **(don't forget to config authServers)**
6. Enable the **Hooks.bungeecord** option in your **AuthMeReloaded config file**
7. Enjoy!

**Please follow these steps and configure the plugin before saying it doesn't work!**
