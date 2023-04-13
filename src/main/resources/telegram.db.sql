CREATE TABLE "bwg_api_key"
(
    "pid"     integer  NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user_id" integer  NOT NULL,
    "veid"    text(20) NOT NULL,
    "api_key" text(50) NOT NULL
);

CREATE UNIQUE INDEX "uniq_user_id" ON "bwg_api_key" ("user_id" ASC);