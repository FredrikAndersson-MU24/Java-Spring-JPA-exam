
INSERT IGNORE INTO users (name) VALUES('Steve'), ('Alex'), ('Zombie'), ('Creeper'), ('Chicken jockey');
INSERT IGNORE INTO channels (name) VALUES('Mining'), ('Crafting'), ('Fighting'), ('Farming'), ('Animals');

INSERT IGNORE INTO posts (title, body, posted, channel_id, user_id)
VALUES
    ('Pixel Soluppgång', ' En ny dag gryr i pixelvärlden. Dags att bryta block och bygga ditt äventyr.', '2024-01-24', 1, 1),
    ('Grottans Eko', 'Djupt nere i grottan ekar ljudet av droppande vatten. Var försiktig för monster.', '2024-01-25', 1, 1),
    ('Drömmar av Redstone', 'Drömmer du om avancerade redstone-kretsar? Bygg din egen maskin i natt.', '2024-01-25', 2, 1),
    ('Skogens Biom', 'Vandra genom den täta skogen. Leta efter sällsynta blommor och glöm inte att hugga ved.', '2024-02-12', 3, 2),
    ('Havets Djup', 'Utforska havets botten. Hitta skatter och undvik drunknade zombies.', '2024-03-11', 3, 2),
    ('Stjärnklara Nätter', 'Titta upp mot den pixelklara natthimlen. En creeper kan smyga bakom dig.', '2024-02-14', 3, 2),
    ('Livets Crafting', 'Varje dag är en ny möjlighet att crafta ditt liv. Samla resurser och bygg din framtid.', '2024-04-05', 5, 2),
    ('Kärlekens Potion', 'Brygg en potion av kärlek och vänskap. Dela den med dina medspelare.', '2024-04-07', 2, 3),
    ('Hoppets Fackla', 'Tänd en fackla i mörkret. Låt hoppet lysa upp din väg genom grottorna.', '2024-04-08', 4, 4),
    ('Byggarens Flöde', 'Låt kreativiteten flöda när du bygger ditt mästerverk. Varje block räknas.', '2024-04-09', 4, 4),
    ('Äventyrets Karta', 'Följ kartan till okända platser. Hitta gömda skatter och besegra bossar.', '2024-04-10', 4, 4),
    ('Tacksamhetens Kista', 'Fyll en kista med alla saker du är tacksam för. Delad glädje är dubbel glädje.', '2024-04-11', 4, 4),
    ('Förändringens Biomer', 'Minecraft-världen förändras ständigt. Anpassa dig till nya biomer och utmaningar.', '2024-05-10', 4, 4),
    ('Inre Fredens Ö', 'Bygg en fridfull ö där du kan koppla av. Låt stressen rinna av dig i vattnet.', '2024-01-28', 4, 4),
    ('Naturens Texturer', 'Uppskatta de vackra texturerna i Minecrafts natur. Varje block har sin egen unika skönhet.', '2024-02-23', 5, 4),
    ('Vänskapens Portal', 'Bygg en portal till dina vänners världar. Tillsammans är ni starkare.', '2024-02-27', 5, 4),
    ('Lärdomens Bok', 'Fyll en bok med alla dina Minecraft-kunskaper. Dela med dig av dina tips och tricks.', '2024-01-28', 5, 5),
    ('Modets Svärd', 'Våga möta farorna med ditt modiga svärd. Besegra draken och bli en hjälte.', '2024-08-08', 5, 5),
    ('Glädjens Explosion', 'Sprid glädje som en explosion av fyrverkerier. Låt alla njuta av festen.', '2024-08-08', 5, 5),
    ('Stillhetens Block', 'Ibland är det bästa att bara sitta still och njuta av utsikten. Låt lugnet infinna sig.', '2024-08-09', 5, 5),
    ('Fantasins Bygge', 'Bygg din vildaste fantasi i Minecraft. Inga gränser, bara kreativitet', '2024-08-09', 5, 5),
    ('Kunskapens Träd', 'Plantera ett träd av kunskap. Låt det växa och ge frukt i form av nya idéer', '2024-08-10', 5, 5),
    ('Tålamodets Gruva', ' Var tålmodig när du gräver efter diamanter. Belöningen kommer till den som väntar.', '2024-09-25', 5, 5),
    ('Inspirationens Kista', 'Fyll en kista med inspiration från andra spelare. Låt dig inspireras och skapa något nytt.', '2024-09-26', 5, 5),
    ('Livets Färd', 'Livet är en färd genom Minecrafts värld. Njut av varje biom och varje äventyr', '2024-09-27', 5, 5)
;