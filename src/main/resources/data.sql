
INSERT IGNORE INTO users (name) VALUES('Steve'), ('Alex'), ('Zombie'), ('Creeper'), ('Chicken jockey');
INSERT IGNORE INTO channels (name) VALUES('Mining'), ('Crafting'), ('Fighting'), ('Farming'), ('Animals');

INSERT IGNORE INTO posts (title, body, posted, channel_id, user_id)
VALUES
    ('Pixel Soluppgång', ' En ny dag gryr i pixelvärlden. Dags att bryta block och bygga ditt äventyr.', current_date(), 1, 1),
    ('Grottans Eko', 'Djupt nere i grottan ekar ljudet av droppande vatten. Var försiktig för monster.', current_date(), 1, 1),
    ('Drömmar av Redstone', 'Drömmer du om avancerade redstone-kretsar? Bygg din egen maskin i natt.', current_date(), 2, 1),
    ('Skogens Biom', 'Vandra genom den täta skogen. Leta efter sällsynta blommor och glöm inte att hugga ved.', current_date(), 3, 2),
    ('Havets Djup', 'Utforska havets botten. Hitta skatter och undvik drunknade zombies.', current_date(), 3, 2),
    ('Stjärnklara Nätter', 'Titta upp mot den pixelklara natthimlen. En creeper kan smyga bakom dig.', current_date(), 3, 2),
    ('Livets Crafting', 'Varje dag är en ny möjlighet att crafta ditt liv. Samla resurser och bygg din framtid.', current_date(), 5, 2),
    ('Kärlekens Potion', 'Brygg en potion av kärlek och vänskap. Dela den med dina medspelare.', current_date(), 2, 3),
    ('Hoppets Fackla', 'Tänd en fackla i mörkret. Låt hoppet lysa upp din väg genom grottorna.', current_date(), 4, 4),
    ('Byggarens Flöde', 'Låt kreativiteten flöda när du bygger ditt mästerverk. Varje block räknas.', current_date(), 4, 4),
    ('Äventyrets Karta', 'Följ kartan till okända platser. Hitta gömda skatter och besegra bossar.', current_date(), 4, 4),
    ('Tacksamhetens Kista', 'Fyll en kista med alla saker du är tacksam för. Delad glädje är dubbel glädje.', current_date(), 4, 4),
    ('Förändringens Biomer', 'Minecraft-världen förändras ständigt. Anpassa dig till nya biomer och utmaningar.', current_date(), 4, 4),
    ('Inre Fredens Ö', 'Bygg en fridfull ö där du kan koppla av. Låt stressen rinna av dig i vattnet.', current_date(), 4, 4),
    ('Naturens Texturer', 'Uppskatta de vackra texturerna i Minecrafts natur. Varje block har sin egen unika skönhet.', current_date(), 5, 4),
    ('Vänskapens Portal', 'Bygg en portal till dina vänners världar. Tillsammans är ni starkare.', current_date(), 5, 4),
    ('Lärdomens Bok', 'Fyll en bok med alla dina Minecraft-kunskaper. Dela med dig av dina tips och tricks.', current_date(), 5, 5),
    ('Modets Svärd', 'Våga möta farorna med ditt modiga svärd. Besegra draken och bli en hjälte.', current_date(), 5, 5),
    ('Glädjens Explosion', 'Sprid glädje som en explosion av fyrverkerier. Låt alla njuta av festen.', current_date(), 5, 5),
    ('Stillhetens Block', 'Ibland är det bästa att bara sitta still och njuta av utsikten. Låt lugnet infinna sig.', current_date(), 5, 5),
    ('Fantasins Bygge', 'Bygg din vildaste fantasi i Minecraft. Inga gränser, bara kreativitet', current_date(), 5, 5),
    ('Kunskapens Träd', 'Plantera ett träd av kunskap. Låt det växa och ge frukt i form av nya idéer', current_date(), 5, 5),
    ('Tålamodets Gruva', ' Var tålmodig när du gräver efter diamanter. Belöningen kommer till den som väntar.', current_date(), 5, 5),
    ('Inspirationens Kista', 'Fyll en kista med inspiration från andra spelare. Låt dig inspireras och skapa något nytt.', current_date(), 5, 5),
    ('Livets Färd', 'Livet är en färd genom Minecrafts värld. Njut av varje biom och varje äventyr', current_date(), 5, 5)
;