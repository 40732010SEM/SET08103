# SET08103 Resit – Kanban Board Setup for Zube.io

This file gives you everything you need to set up a proper Kanban board on **Zube.io** for the individual resit coursework.

Zube works by syncing with **GitHub Issues**. There is no direct “upload board” file.  
The correct workflow is:

1. Create / use your GitHub repository for the coursework.
2. Connect the repository to a new Zube Project.
3. Create the issues listed below (they become Zube cards automatically).
4. Customise the Kanban columns (recommended set below).
5. Drag cards into the correct columns / sprints.

---

## Recommended Kanban Columns (customise in Workspace Settings)

Use these columns (left → right):

| Column          | Purpose                                      | State   |
|-----------------|----------------------------------------------|---------|
| **Inbox**       | Newly created / imported cards               | Open    |
| **Product Backlog** | Prioritised but not yet planned           | Open    |
| **Sprint Backlog / Ready** | Selected for the current sprint     | Open    |
| **In Progress** | Actively being worked on                     | Open    |
| **Review / Testing** | Code review, unit/integration tests, PR  | Open    |
| **Done**        | Completed and accepted                       | Closed  |

You can also add a **Blocked** column if you wish.

---

## How to import into Zube

### Option A – Recommended (manual but clean)
1. Go to your GitHub repository → **Issues** → **New issue**.
2. Copy-paste each issue below (title + body).
3. Add labels (suggested labels are listed).
4. Once the issues exist, open Zube → create a Project → select your repository as Source.
5. Zube will import all open issues as cards into the **Inbox**.
6. Move them into the correct columns.

### Option B – Faster with CSV
Use the accompanying file `zube-github-issues.csv`.  
You can:
- Create issues one-by-one from the CSV, **or**
- Use a GitHub CLI / script / browser extension that bulk-creates issues from CSV, **or**
- Just keep the CSV as your master backlog list and create issues as you start each sprint.

---

## Suggested Labels (create these in GitHub)

- `type:user-story`
- `type:task`
- `type:chore`
- `type:epic`
- `priority:high`
- `priority:medium`
- `priority:low`
- `area:setup`
- `area:reports`
- `area:testing`
- `area:docs`
- `area:ci-cd`
- `sprint:1`
- `sprint:2`
- `sprint:3`

---

## Product Backlog – Epics & User Stories

### Epic 1: Project Infrastructure & DevOps Setup
**Goal:** Satisfy all the “process” marking criteria (GitFlow, Maven, Docker, GitHub Actions, badges, etc.)

#### US-01 – Project scaffolding
**As a** developer  
**I want** a Maven project that builds a self-contained executable JAR  
**So that** the application can be run and deployed easily.

**Acceptance Criteria:**
- [ ] Maven `pom.xml` configured for Java 17
- [ ] `mvn clean package` produces a fat JAR
- [ ] Main class runs and prints a simple message

**Labels:** `type:user-story`, `area:setup`, `priority:high`

---

#### US-02 – Dockerfile
**As a** developer  
**I want** a working Dockerfile  
**So that** the application can be containerised.

**Acceptance Criteria:**
- [ ] Dockerfile builds successfully
- [ ] Container runs the JAR
- [ ] Database connection works from inside the container (or clear instructions)

**Labels:** `type:user-story`, `area:ci-cd`, `priority:high`

---

#### US-03 – GitFlow branches
**As a** developer  
**I want** the correct GitFlow branches  
**So that** the repository follows the module standard.

**Acceptance Criteria:**
- [ ] `master`, `develop`, and `release` branches exist
- [ ] Default branch policy documented

**Labels:** `type:chore`, `area:setup`, `priority:high`

---

#### US-04 – GitHub Actions CI
**As a** developer  
**I want** GitHub Actions that build the JAR and Docker image  
**So that** every push is automatically verified.

**Acceptance Criteria:**
- [ ] Workflow runs on push to `master` and `develop`
- [ ] Builds the Maven JAR
- [ ] Builds and (optionally) pushes the Docker image
- [ ] Status badges appear in README

**Labels:** `type:user-story`, `area:ci-cd`, `priority:high`

---

#### US-05 – Release & Code of Conduct
**As a** developer  
**I want** a GitHub Release and a Code of Conduct  
**So that** the project meets the checklist criteria.

**Acceptance Criteria:**
- [ ] At least one Release created on GitHub
- [ ] `CODE_OF_CONDUCT.md` present
- [ ] License (Apache-2.0 recommended) present

**Labels:** `type:chore`, `area:docs`, `priority:medium`

---

### Epic 2: Database Connection & Core Reports

#### US-06 – Connect to World database
**As a** developer  
**I want** the application to connect to the MySQL world database  
**So that** reports can be generated from real data.

**Acceptance Criteria:**
- [ ] Connection string configurable (env vars or properties)
- [ ] Connection tested successfully
- [ ] Graceful error handling if database is unavailable

**Labels:** `type:user-story`, `area:setup`, `priority:high`

---

#### US-07 – All countries by population (desc)
**As a** user  
**I want** to see all countries ordered by largest population to smallest  
**So that** I can understand global population ranking.

**Acceptance Criteria:**
- [ ] Report shows: Code, Name, Continent, Region, Population, Capital
- [ ] Ordered by Population DESC
- [ ] Output is clear (console or file)

**Labels:** `type:user-story`, `area:reports`, `priority:high`  
**Requirement ID:** 1

---

#### US-08 – All cities by population (desc)
**As a** user  
**I want** to see all cities ordered by largest population to smallest  
**So that** I can identify the biggest cities.

**Acceptance Criteria:**
- [ ] Report shows: Name, Country, District, Population
- [ ] Ordered by Population DESC

**Labels:** `type:user-story`, `area:reports`, `priority:high`  
**Requirement ID:** 2

---

#### US-09 – All capital cities by population (desc)
**As a** user  
**I want** to see all capital cities ordered by largest population to smallest  
**So that** I can compare capital city sizes.

**Acceptance Criteria:**
- [ ] Report shows: Name, Country, Population
- [ ] Only capital cities included
- [ ] Ordered by Population DESC

**Labels:** `type:user-story`, `area:reports`, `priority:high`  
**Requirement ID:** 3

---

#### US-10 – Top N populated cities (user provides N)
**As a** user  
**I want** to request the top N most populated cities  
**So that** I can focus on the largest cities only.

**Acceptance Criteria:**
- [ ] User can supply N (command-line argument or interactive input)
- [ ] Report shows top N cities with required columns
- [ ] Handles invalid N gracefully

**Labels:** `type:user-story`, `area:reports`, `priority:high`  
**Requirement ID:** 4

---

#### US-11 – Population of people living / not living in cities per country
**As a** user  
**I want** the population breakdown (total, in cities, not in cities) for each country  
**So that** I can see urbanisation levels.

**Acceptance Criteria:**
- [ ] Shows country name, total population, city population + %, non-city population + %
- [ ] Calculations are correct

**Labels:** `type:user-story`, `area:reports`, `priority:high`  
**Requirement ID:** 5

---

### Epic 3: Additional Population Queries

#### US-12 – Simple population lookups
**As a** user  
**I want** to query the population of the world, a continent, a region, a country, a district, or a city  
**So that** I can get quick totals.

**Acceptance Criteria:**
- [ ] World population
- [ ] Continent population
- [ ] Region population
- [ ] Country population
- [ ] District population
- [ ] City population
- [ ] Clear output for each

**Labels:** `type:user-story`, `area:reports`, `priority:medium`

---

#### US-13 – Language speakers (Chinese, English, Spanish)
**As a** user  
**I want** the number of speakers of Chinese, English and Spanish ordered from greatest to smallest, including percentage of world population  
**So that** I can compare major languages.

**Acceptance Criteria:**
- [ ] Only the three languages requested
- [ ] Ordered by number of speakers DESC
- [ ] Percentage of world population shown
- [ ] Correct aggregation from `countrylanguage` table

**Labels:** `type:user-story`, `area:reports`, `priority:medium`

---

### Epic 4: Testing & Quality

#### US-14 – Unit tests
**As a** developer  
**I want** suitable unit tests  
**So that** individual components are verified.

**Acceptance Criteria:**
- [ ] Unit tests for key report methods / calculators
- [ ] Tests run with Maven (`mvn test`)
- [ ] Reasonable coverage

**Labels:** `type:user-story`, `area:testing`, `priority:high`

---

#### US-15 – Integration tests
**As a** developer  
**I want** integration tests against the database  
**So that** end-to-end report generation is verified.

**Acceptance Criteria:**
- [ ] Tests that actually query the database (or a test container)
- [ ] Tests run in GitHub Actions
- [ ] Failures are visible in CI

**Labels:** `type:user-story`, `area:testing`, `priority:high`

---

### Epic 5: Documentation & Final Deliverable

#### US-16 – README with requirements table
**As a** marker  
**I want** a clear README that states how many of the 8 requirements are met and shows evidence  
**So that** I can assess the submission quickly.

**Acceptance Criteria:**
- [ ] Statement: “X requirements of 8 have been implemented, which is Y%”
- [ ] Markdown table with columns: ID | Name | Met | Screenshot
- [ ] Screenshots embedded or linked for every “Yes”
- [ ] Correct badges (build status master/develop, coverage, release, license)

**Labels:** `type:user-story`, `area:docs`, `priority:high`

---

#### US-17 – Use cases & diagram
**As a** developer  
**I want** full use cases and a use-case diagram  
**So that** the requirements analysis is complete.

**Acceptance Criteria:**
- [ ] Use cases written for the main reports
- [ ] Use-case diagram created (PlantUML, draw.io, or similar) and committed

**Labels:** `type:user-story`, `area:docs`, `priority:medium`

---

## Suggested Sprint Breakdown (Individual)

**Sprint 1 (Setup & Foundation)**  
US-01, US-02, US-03, US-04, US-05, US-06

**Sprint 2 (Core Reports)**  
US-07, US-08, US-09, US-10, US-11

**Sprint 3 (Extra queries + Testing + Docs)**  
US-12, US-13, US-14, US-15, US-16, US-17

Move cards from Product Backlog → Sprint Backlog at the start of each sprint, then through In Progress → Review/Testing → Done.

---

## Quick Start Checklist for Zube

- [ ] GitHub repository created and code pushed
- [ ] Zube account created / logged in with GitHub
- [ ] New Zube Project created and your repository selected as Source
- [ ] Issues created from the list above
- [ ] Columns customised to the recommended set
- [ ] First sprint created and cards moved into it
- [ ] Link to Zube workspace ready for the Moodle text-file submission

---

Good luck with the resit!
Once the issues are in GitHub, Zube will keep the board in sync automatically.
