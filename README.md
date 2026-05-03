🔥 1. vars/ (MOST IMPORTANT)

This is where most DevOps engineers spend their time.

What it is:
- Global reusable pipeline functions
- Each .groovy file becomes a callable step in Jenkinsfile

Example:
vars/
├── buildDockerImage.groovy
├── dockerLogin.groovy
├── dockerPush.groovy
├── publishDocker.groovy

How you use it:
publishDocker(...)

----------------------------------------------//


🧠 2. src/ (for structured Groovy/Java code)

What it is:
- Proper Groovy classes (like Java packages)
- Used for reusable logic, utilities, APIs, helpers

Example:
src/
└── com/
    └── company/
        ├── DockerUtils.groovy
        ├── AwsUtils.groovy

When to use:
- Complex logic
- Reusable business rules
- Not direct pipeline steps

----------------------------------------------//


📦 3. resources/ (static files)

What it is:
- Non-Groovy files used by scripts

Examples:
resources/
├── docker/

│   └── Dockerfile.template
├── scripts/
│   └── deploy.sh

How it’s used:
libraryResource 'scripts/deploy.sh'