-- 新增根级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),NULL,'Flora',0),
       (UUID(),NULL,'Fruity',0),
       (UUID(),NULL,'Sour/\nFermented',0),
       (UUID(),NULL,'Green/\nVegetative',0),
       (UUID(),NULL,'Other',0),
       (UUID(),NULL,'Roasted',0),
       (UUID(),NULL,'Spices',0),
       (UUID(),NULL,'Nutty/\nCocoa',0),
       (UUID(),NULL,'Sweet',0);

-- 新增 Flora 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cd4320-289d-11ec-8405-00e04c89feb4','Black Tea',1),
       (UUID(),'76cd4320-289d-11ec-8405-00e04c89feb4','Floral',0);

-- 新增 Fruity 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf17ea-289d-11ec-8405-00e04c89feb4','Berry',0),
       (UUID(),'76cf17ea-289d-11ec-8405-00e04c89feb4','Dried Fruit',0),
       (UUID(),'76cf17ea-289d-11ec-8405-00e04c89feb4','Other Fruit',0),
       (UUID(),'76cf17ea-289d-11ec-8405-00e04c89feb4','Citrus Fruit',0);


-- 新增 Sour/\nFermented 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf1920-289d-11ec-8405-00e04c89feb4','Sour',0),
       (UUID(),'76cf1920-289d-11ec-8405-00e04c89feb4','Alcohol/\nFermented',0);

-- 新增 Green/\nVegetative 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf194f-289d-11ec-8405-00e04c89feb4','Olive Oil',1),
       (UUID(),'76cf194f-289d-11ec-8405-00e04c89feb4','Raw',1),
       (UUID(),'76cf194f-289d-11ec-8405-00e04c89feb4','Green/\nVegetative',0),
       (UUID(),'76cf194f-289d-11ec-8405-00e04c89feb4','Bean',1);

-- 新增 Other 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf1975-289d-11ec-8405-00e04c89feb4','Papery/Musty',0),
       (UUID(),'76cf1975-289d-11ec-8405-00e04c89feb4','Chemical',0);

-- 新增 Roasted 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf199d-289d-11ec-8405-00e04c89feb4','Pipe Tobacco',1),
       (UUID(),'76cf199d-289d-11ec-8405-00e04c89feb4','Tobacco',1),
       (UUID(),'76cf199d-289d-11ec-8405-00e04c89feb4','Burnt',0),
       (UUID(),'76cf199d-289d-11ec-8405-00e04c89feb4','Cereal',0);


-- 新增 Spices 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf19c2-289d-11ec-8405-00e04c89feb4','Pungent',1),
       (UUID(),'76cf19c2-289d-11ec-8405-00e04c89feb4','Pepper',1),
       (UUID(),'76cf19c2-289d-11ec-8405-00e04c89feb4','Brown Spice',0);

-- 新增 Nutty/\nCocoa 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf19e9-289d-11ec-8405-00e04c89feb4','Nutty',0),
       (UUID(),'76cf19e9-289d-11ec-8405-00e04c89feb4','Cocoa',0);

-- 新增 Sweet 一级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'76cf1a19-289d-11ec-8405-00e04c89feb4','Brown Sugar',0),
       (UUID(),'76cf1a19-289d-11ec-8405-00e04c89feb4','Vanilla',1),
       (UUID(),'76cf1a19-289d-11ec-8405-00e04c89feb4','Vanillin',1),
       (UUID(),'76cf1a19-289d-11ec-8405-00e04c89feb4','Overall Sweet',1),
       (UUID(),'76cf1a19-289d-11ec-8405-00e04c89feb4','Sweet Aromatics',1);

-- 新增 Floral 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1370397f-28a0-11ec-8405-00e04c89feb4','Chamomile',1),
       (UUID(),'1370397f-28a0-11ec-8405-00e04c89feb4','Rose',1),
       (UUID(),'1370397f-28a0-11ec-8405-00e04c89feb4','Jasmine',1);


-- 新增 Berry 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1375ce1b-28a0-11ec-8405-00e04c89feb4','Blackberry',1),
       (UUID(),'1375ce1b-28a0-11ec-8405-00e04c89feb4','Raspberry',1),
       (UUID(),'1375ce1b-28a0-11ec-8405-00e04c89feb4','Blueberry',1),
       (UUID(),'1375ce1b-28a0-11ec-8405-00e04c89feb4','Strawberry',1);


-- 新增 Dried Fruit 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1375e53b-28a0-11ec-8405-00e04c89feb4','Raisin',1),
       (UUID(),'1375e53b-28a0-11ec-8405-00e04c89feb4','Prune',1);


-- 新增 Other Fruit 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Coconut',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Cherry',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Pomegranate',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Pineapple',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Grape',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Apple',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Peach',1),
       (UUID(),'1375e5de-28a0-11ec-8405-00e04c89feb4','Pear',1);


-- 新增 Citrus Fruit 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1375e60f-28a0-11ec-8405-00e04c89feb4','Grapefruit',1),
       (UUID(),'1375e60f-28a0-11ec-8405-00e04c89feb4','Orange',1),
       (UUID(),'1375e60f-28a0-11ec-8405-00e04c89feb4','Lemon',1),
       (UUID(),'1375e60f-28a0-11ec-8405-00e04c89feb4','Lime',1);


-- 新增 Sour 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Sour Aromatics',1),
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Acetic Acid',1),
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Butyric Acid',1),
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Isovaleric Acid',1),
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Citric Acid',1),
       (UUID(),'13796ff6-28a0-11ec-8405-00e04c89feb4','Malic Acid',1);


-- 新增 Alcohol/\nFermented 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'137986e4-28a0-11ec-8405-00e04c89feb4','Winey',1),
       (UUID(),'137986e4-28a0-11ec-8405-00e04c89feb4','Whiskey',1),
       (UUID(),'137986e4-28a0-11ec-8405-00e04c89feb4','Fremented',1),
       (UUID(),'137986e4-28a0-11ec-8405-00e04c89feb4','Overripe',1);


-- 新增 Green/\nVegetative 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Under-ripe',1),
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Peapod',1),
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Dark Green',1),
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Vegetative',1),
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Hay-like',1),
       (UUID(),'13825241-28a0-11ec-8405-00e04c89feb4','Herb-like',1);

-- 新增 Papery/Musty 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Stale',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Cardboard',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Papery',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Woody',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Moldy/Damp',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Musty/Dusty',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Musty/Earthy',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Animalic',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Meaty Brothy',1),
       (UUID(),'1386bd8f-28a0-11ec-8405-00e04c89feb4','Phenolic',1);

-- 新增 Chemical 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Bitter',1),
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Salty',1),
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Medicinal',1),
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Petroleum',1),
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Skunky',1),
       (UUID(),'1386c826-28a0-11ec-8405-00e04c89feb4','Rubber',1);


-- 新增 Burnt 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'138b7e09-28a0-11ec-8405-00e04c89feb4','Acrid',1),
       (UUID(),'138b7e09-28a0-11ec-8405-00e04c89feb4','Ashy',1),
       (UUID(),'138b7e09-28a0-11ec-8405-00e04c89feb4','Smoky',1),
       (UUID(),'138b7e09-28a0-11ec-8405-00e04c89feb4','Brown, Roast',1);


-- 新增 Cereal 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'138b7e76-28a0-11ec-8405-00e04c89feb4','Grain',1),
       (UUID(),'138b7e76-28a0-11ec-8405-00e04c89feb4','Malt',1);


-- 新增 Brown Spice 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'138eb29e-28a0-11ec-8405-00e04c89feb4','Anise',1),
       (UUID(),'138eb29e-28a0-11ec-8405-00e04c89feb4','Nutmeg',1),
       (UUID(),'138eb29e-28a0-11ec-8405-00e04c89feb4','Cinnamon',1),
       (UUID(),'138eb29e-28a0-11ec-8405-00e04c89feb4','Clove',1);


-- 新增 Nutty 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'13941168-28a0-11ec-8405-00e04c89feb4','Peanuts',1),
       (UUID(),'13941168-28a0-11ec-8405-00e04c89feb4','Hazelnut',1),
       (UUID(),'13941168-28a0-11ec-8405-00e04c89feb4','Almond',1);


-- 新增 Cocoa 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'13942408-28a0-11ec-8405-00e04c89feb4','Chocolate',1),
       (UUID(),'13942408-28a0-11ec-8405-00e04c89feb4','Dark Chocolate',1);


-- 新增 Brown Sugar 二级子级数据
insert into sunburst_drink(id, parent_id, name, value)
values
       (UUID(),'139fa9dc-28a0-11ec-8405-00e04c89feb4','Molasses',1),
       (UUID(),'139fa9dc-28a0-11ec-8405-00e04c89feb4','Maple Syrup',1),
       (UUID(),'139fa9dc-28a0-11ec-8405-00e04c89feb4','Caramelized',1),
       (UUID(),'139fa9dc-28a0-11ec-8405-00e04c89feb4','Honey',1);