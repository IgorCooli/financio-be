
//    MIGRATIONS:

db.user_document.createIndex( { "username": 1 }, { unique: true } );
db.session_document.createIndex( { "username": 1 }, { unique: true } );
db.session_document.createIndex( { "expirationDate": 1 }, {expireAfterSeconds: 600 } );