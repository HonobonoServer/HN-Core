This software is released under the MIT License, see LICENSE-MIT.md.

This software includes the work that is distributed in the Apache License 2.0, see LICENSE-Apache.md.

この製品には MaxMind が作成した GeoLite2 データが含まれており、
<a href="http://www.maxmind.com">http://www.maxmind.com</a> から入手いただけます。

このプラグインについて

このプラグインはHonobonoServer(hn-server.ddo.jp)用のpluginです。
そのためソースはオープンソースとして管理しますがコンパイル後のjarファイルの二次配布は禁止とします。
またこのプラグインはMaxMindDBを使用します。
Pluginを起動後[ http://dev.maxmind.com/ja/geolite2/ ]よりGeoLite2-City.mmdbをダウンロードし
plugin/HN-Core/MaxMindDB内に保存してください。
またこのプラグインではTwitter4Jを使用しております。

コマンドについて

hnget
-
	permission: hncore.get
	command: /hnget <blockID> <data>
	説明: 1.8では置けなくなったblock等を置くためのコマンドです

show hide
-
	permission: hncore.show , hncore.hide
	command: /show , /hide
	説明: その名の通り姿を消したりあらわしたりするコマンドです

mute unmute mutelist
-
	permission: hncore.mute , hncore.unmute , hncore.mutelist , hncore.mute.tell
	command: /mute <プレイヤー> , /unmute <プレイヤー> , /mutelist
	説明: muteコマンドを実装するとそのプレイヤーの発言を禁止します
		  <hncore.mute.tell>のパーミッションを持っているとmuteしているプレイヤーの発言が見れます

a
-
	permission: hncore.adminchat
	command: /a
	説明: /aを実行するとAdminChatに入ります。もう一度実行すると抜けます
		  入っているときに発言したことはhncore.adminchatを持っている人にしか見えません

al
-
	permission: hncore.allremove
	command: /al <range>
	説明: <range>分の範囲にいるItem,すべてのエンティティを削除します

freeze unfreeze
-
	permission: hncore.freeze , hncore.unfreeze
	command: /freeze <プレイヤー> , /unfreeze <プレイヤー>
	説明: プレイヤーの動きを制限します
		  ちなみにガバガバ実装なので変な動きをします
swp twp lwp
-
	permission: hncore.setwp , hncore.tpwp , hncore.listwp
	command: /swp <Name> , /twp <Name> , /lwp
	使い方: swpで地点の登録 twpで地点へテレポート lwpで地点一覧を表示します

コンフィグについて
	詳しくは起動後に生成されるconfig.ymlを参照して下さい。
