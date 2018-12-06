FROM airhacks/glassfish
COPY ./target/wild.war ${DEPLOYMENT_DIR}
