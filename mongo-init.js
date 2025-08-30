db.getSiblingDB('admin').auth({
    user:process.env.MONGO_INITDB_ROOT_USERNAME,
    pwd: process.env.MONGO_INITDB_ROOT_PASSWORD,
    mechanism: "SCRAM-SHA-256"
});

db.createUser({
    user: process.env.MONGO_USER,
    pwd: process.env.MONGO_PASSWORD,
    roles: [
        { role: "readWrite", db: process.env.MONGO_INITDB_DATABASE }
    ]
});

