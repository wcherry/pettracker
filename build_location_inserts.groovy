long now = new Date().getTime()
long ONE_HR = 60L * 60L

println "use petsrus;"

// Premium owner more then 30 days of history
(0..800).each{
    println "insert into locations (owner_id,pet_id,longitude,latitude,created_at) values (1,1,-119.${it},37.639095, FROM_UNIXTIME(${now/1000-it*ONE_HR}));"
}


// Free owner more then 1 day of history
(0..30).each{
    println "insert into locations (owner_id,pet_id,longitude,latitude,created_at) values (2,2,-119.${it},37.639095, FROM_UNIXTIME(${now/1000-it*ONE_HR}));"
}

// Free owner with two pets - Pet #1
(0..30).each{
    println "insert into locations (owner_id,pet_id,longitude,latitude,created_at) values (3,3,-119.${it},37.639095, FROM_UNIXTIME(${now/1000-it*ONE_HR}));"
}

// Free owner with two pets - Pet #2
(0..30).each{
    println "insert into locations (owner_id,pet_id,longitude,latitude,created_at) values (3,4,-119.${it},37.639095, FROM_UNIXTIME(${now/1000-it*ONE_HR}));"
}