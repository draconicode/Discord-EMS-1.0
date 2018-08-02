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
  \
  \
What i want to add in the future:\
  A list of bots if you choose to save the token, like that you will be able to choose the bot you want in a list.\
  Saving the entire embed in a json file (or more if needed)\
  \
If you notice any bugs, or if you have suggestions, you can join this Discord server : https://discord.gg/2sa4tnq
  
August 02 2018\
	1.2: Saving Update\
		Features:\
			Added saving and loading entire embeds.\
			(Finaly) made the reset button reset the tool.\
			Added offline mode.\
			Changed the icon and the splash screen.\
			Added icons the the menus.\
			"Change token" option now disconect the bot only if connecting to an other bot.\
\
		Bugfixes:\
			Crash without showing anithing when failed attemp to connect to discord servers\
			"Make clickable" option still editing the text even without required http prefix\
			Hexadecimal field not keeping the value when exiting color chooser\
			Change token window is closable (bot disconected, errors when trying to send an embed)\
			Main GUI still accessible when the splashcreen is displayed\
			Author and Footer textfields enabled without the author/footer checkBox selected\
			missing "return;" statement after showing an error when the embedbuilder is empty\
			Normal field window saving the field even if empty\
			Broken if token .properties file does not exist at startup\
