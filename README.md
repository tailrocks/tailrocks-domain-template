# tailrocks-domain-template

The sample project for storing Java domain entity modules.

## jOOQ Modules

Each `*-jooq` module contains `init-db.sh` script, which will create a new database (if it's not created before) for
that module and apply migrations to that database. If you need to get a new clean database, you can pass `clean`
parameter to init-db script, this will drop existing database (if it exists) and create a new one with apply migrations.

## License

tailrocks-domain-template is available under the [MIT license](https://opensource.org/licenses/MIT).
See [LICENSE](LICENSE) for the full license text.
