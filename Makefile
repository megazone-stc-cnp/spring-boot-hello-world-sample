.PHONY: build
build:
	mvn clean compile

.PHONY: package
package:
	mvn clean package -DskipTests

.PHONY: run
run:
	mvn spring-boot:run