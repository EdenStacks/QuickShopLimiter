# 📊 QuickShopLimiter V2.1.1 📊

This plugin allows setting limit on QuickShop shop's.
In this version, the plugin check to reset shops at 6AM. Be sure
that your server is on at this moment. I am going to add this 
parameter in config.yml.

Tested on paper 1.18.2 with QuickShop v5.1.0.5.


## Dependency
- QuickShop


## Configuration

You can change different parameters from config.yml.
<details>
    <summary>config.yml</summary>

```
# You can per default change this between fr and en.
language: fr
# You can replace this by your time zone.
# See below a list of different time zone :
# - Europe/Amsterdam
# - Europe/Andorra
# - Europe/Astrakhan
# - Europe/Athens
# - Europe/Belfast
# - Europe/Belgrade
# - And more ...
# By default it will use your server time zone.
time-zone: Europe/Paris
```
</details>


## Permissions
- `quickshoplimiter.command.create` Allow to create a limit.
- `quickshoplimiter.command.reload` Allow reloading configuration file.
- `quickshoplimiter.command.reset` Allow reset a limit.
- `quickshoplimiter.command.delete` Allow to delete a limit.
- `quickshoplimiter.command.info` Allow seeing info on a limit.
- `quickshoplimiter.command.about` Allow checking information on plugin.
- `quickshoplimiter.command.modify` Allow using this (useless).
- `quickshoplimiter.command.modify.timingtype` Allow modifying timing type of shop.
- `quickshoplimiter.command.modify.limitamount` Allow modifying limit amount of shop.
- `quickshoplimiter.command.modify.interval` Allow modifying interval of shop.
- `quickshoplimiter.command.modify.limittype` Allow modifying limit type of shop.
- `quickshoplimiter.command.modify.permission` Allow modifying permission of shop.

## Authors

- [@LudovicAns](https://github.com/LudovicAns)


[![Logo](https://i.imgur.com/QXuYtex.png)](https://github.com/EdenStacks)

