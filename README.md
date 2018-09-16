# Discord-EMS
A tool to send custom Embed-Messages to Discord using a bot.
Made with Java

The description describes well what this tool does.\
Features:\
  Embed Title\
  Embed Description\
  Image\
  Thumbnail\
  Autor(Name and icon)\
  Footer(Name and icon)\
  TimeStamp\
  Fields\
  Blank Field\
  Make text clickable (redirecting to a custom link)\
  Custom color\
  Switching between bots without having to restart the tool\
  Guild and TextChannel selection\
  Tag everyone\
  Delayed messages\
  \
  \
What i want to add in the future:\
  Auto updater & installer\
  \
If you notice any bugs, or if you have suggestions, you can join this Discord server : https://discord.gg/2sa4tnq
  

September 16 2018\
	1.3: Optimization Update\
		Features:\
			Moved the Guild/Channel selection to a separate window (adress window)\
			Added "tag @everyone" check box to the adress window\
			Added a bot list (you can now save more than one bot, yay !)\
			Custom JOptionPane replacement\
			Removed duplicate class for editing fields\
			Moved the methods to build the embed builder to a separate class (300+ lines)\
			Remade the status display\
			The tool now uses JRE Cross-plateform look and feel on Linux distros\
			Added a delaying function (HH:mm) for sending messages at a selected hour (by steps of 5mins).\
\
		Bugfixes:\
			Opening color chooser or fields window indefinitly.\
			Fixed a bug that occured while loading an embed (Field and blankfield arraylist returning null when empty)\
			Fixed missing character in hex color code when int below 16 (0F was displaying as F)\
			Fixed fields names after loading an embed.\
			Fixed fields name duplicating in the Fields combobox when editing one, therefore, duplicating this field in the embed message\
			Main GUI still accessible when the splashcreen is displayed (yep, again.)\
			EmbedBuilder crashing when the image/thumbnail url is not starting with http/https\
