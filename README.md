<p align="center">
    <h1 align="center">WAR IN UKRAINE πΊπ¦</h1>
    <p><a href="https://en.wikipedia.org/wiki/Russo-Ukrainian_War">Russo-Ukrainian War</a> which started in Feb 2014 and escalated since 8 years into a full-scale war against Ukraine on 24th of February,  became a top cause in the international main stream nowadays. The Ukrainians fight and die to save their democracy, freedom, and sovereignty.</p>
    <p>Please take action to stop the war in Ukraine, there are many ways how you can help: https://war.ukraine.ua</p>
</p>

---

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

The main reason why we separate jOOQ modules into one repository because the Gradle jOOQ plugin doesn't work well with 
Gradle cache, it destroys the cache every time by generating Java classes from the database structure. By using a 
separate repository we can improve the cache, by generating the jar package once and upload it to the Maven repository
and reuse that module inside related Java or Kotlin microservice. This speedup running the Application or a single test
a lot.

## License

`tailrocks-domain-template` is available under the [MIT license](https://opensource.org/licenses/MIT).
See [LICENSE](LICENSE) for the full license text.
