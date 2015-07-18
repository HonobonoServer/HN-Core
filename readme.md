This software is released under the MIT License, see LICENSE.md.

このプラグインについて

このプラグインはHonobonoServer(hn-server.ddo.jp)用のpluginです。
そのためソースはオープンソースとして管理しますがコンパイル後のjarファイルの二次配布は禁止とします。

コマンドについて

hnconfig
-
	permission: hncore.config
	command: /hnconfig <変えたいものまでの位置> <入る値>
	説明: コンフィグを変更するコマンドです
			コンフィグにおいて
			test1:
			 test2: true
			のようになっている場合
			/hnconfig test1.test2 trueのように入力して下さい

hnreload
-
	permission: hncore.reload
	command: /hnreload
	説明: コンフィグを再読み込みするコマンドです
		  動作がおかしくなった場合には一度実行してみてください

hnget
-
	permission: hncore.get
	command: /hnget <blockID> <data>
	説明: 1.8では置けなくなったblock等を置くためのコマンドです

hntp
-
	permission: hncore.hntp
	command: /hntp <プレイヤー> or /hntp <X> <Y> <Z> (<World>)
	説明: 無音でhnするコマンドです
		  他PluginでTpコマンドで音が出る場合に活用しましょう


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

ir al
-
	permission: hncore.itemremove , hncore.allremove
	command: /ir <range> , /al <range>
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
home
-
	permission: hncore.home
	command: /home
	説明: Bedへ戻るコマンドです /home <player> とするとそのplayerのBedまで戻れます

コンフィグについて
	詳しくは起動後に生成されるconfig.ymlを参照して下さい。
