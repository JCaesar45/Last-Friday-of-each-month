# Last Friday Suite

A portfolio-grade reference project that solves a single, elegant problem: **find the last Friday of any month**. The difference is in the presentation. This repository pairs a cinematic frontend with production-ready backends in Python, TypeScript, and Java.

## What You Are Looking At

- `index.html` — A self-contained, luxury-grade web application. HTML, CSS, and JavaScript live in one file so it runs anywhere: double-click it, host it on S3, or drop it into a static-site generator.
- `backend-python/` — Flask microservice with explicit `datetime` arithmetic.
- `backend-typescript/` — Express service written in TypeScript with strict typing.
- `backend-java/` — Spring Boot service using `java.time` and `TemporalAdjusters`.

## The Algorithm

The core idea is identical across every implementation:

1. Identify the final calendar day of the target month.
2. Compute how many days you must walk backward to reach the preceding Friday.
3. Subtract that offset from the last day.

In JavaScript this reads as:

```javascript
const lastDay = new Date(year, month, 0);
const daysBack = (lastDay.getDay() - 5 + 7) % 7;
return lastDay.getDate() - daysBack;
```

The `+ 7) % 7` guard handles Sunday through Thursday without branching, which keeps the function branchless and easy to reason about.

## Running the Project

### Frontend

Open `index.html` in any modern browser. No build step is required.

### Python Backend

```bash
cd backend-python
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
python app.py
```

Test with:

```bash
curl "http://localhost:5001/last-friday?year=2018&month=1"
```

### TypeScript Backend

```bash
cd backend-typescript
npm install
npm run build
npm start
```

Test with:

```bash
curl "http://localhost:5002/last-friday?year=2018&month=1"
```

### Java Backend

```bash
cd backend-java
./mvnw spring-boot:run
```

Test with:

```bash
curl "http://localhost:8080/last-friday?year=2018&month=1"
```

## Test Suite

All implementations satisfy the Rosetta Code canonical test vectors:

| Year | Month | Last Friday |
|------|-------|-------------|
| 2018 | 1     | 26          |
| 2017 | 2     | 24          |
| 2012 | 3     | 30          |
| 1900 | 4     | 27          |
| 2000 | 5     | 26          |
| 2006 | 6     | 30          |
| 2010 | 7     | 30          |
| 2005 | 8     | 26          |

## Why This Exists

The original challenge is trivial. The value here is showing how a trivial challenge can be wrapped in a memorable product: cinematic visuals, consistent cross-runtime logic, and clean architecture. It is the kind of artifact you can show to a company, a client, or a conference audience and have them remember your name.

## References

MDN Web Docs. (2024). *Date - JavaScript*. https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date

Node.js Foundation. (2024). *Node.js documentation*. https://nodejs.org/en/docs/

Oracle. (2024). *The Java Tutorials: Date and time*. https://docs.oracle.com/javase/tutorial/datetime/

Python Software Foundation. (2024). *datetime - Basic date and time types*. https://docs.python.org/3/library/datetime.html

TypeScript. (2024). *TypeScript documentation*. https://www.typescriptlang.org/docs/
