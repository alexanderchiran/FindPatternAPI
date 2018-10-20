db.regexModel.insert([{
    "_id": NumberInt("1"),
    "description": "HashTag",
    "value": "(?<=[\\s>])#(\\d*[A-Za-z_]+\\d*)\\b(?!;)",
    "_class": "com.belatrixsf.findpatternapi.helpers.RegexModel"
}])

db.regexModel.insert([{
    "_id": NumberInt("2"),
    "description": "Account Twitter",
    "value": "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9-_]+)",
    "_class": "com.belatrixsf.findpatternapi.helpers.RegexModel"
}])
