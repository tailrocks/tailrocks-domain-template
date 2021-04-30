# tailrocks-domain-template

The sample project for storing Java domain entity modules.

## How to use

1. Click on `Use this template` button in GitHub page to create your own repository based on this template. After that go
through `FIXME` label in the comments and replace with correct value.

2. Everytime when you need to add a new module, you need to copy `tailrocks-example-flyway-migrations` and
`tailrocks-example-jooq` folders and rename to your suitable project name. On top of that you also need to go through
all `FIXME` labels. After that please don't forget to add your modules to [settings.gradle.kts](settings.gradle.kts)
and to the `publishingProjects` list in the root [build.gradle.kts](build.gradle.kts) file.

## jOOQ Modules

Each `*-jooq` module contains `init-db.sh` script, which will create a new database (if it's not created before) for
that module and apply migrations to that database. If you need to get a new clean database, you can pass `clean`
parameter to init-db script, this will drop existing database (if it exists) and create a new one with apply migrations.

## License

tailrocks-domain-template is available under the [MIT license](https://opensource.org/licenses/MIT).
See [LICENSE](LICENSE) for the full license text.
