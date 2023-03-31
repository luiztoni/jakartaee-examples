FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

# Add a Liberty server configuration including all necessary features
COPY --chown=1001:0  server.xml /config/

# This script adds the requested XML snippets to enable Liberty features and grow the image to be fit-for-purpose.
# This option is available only in the 'kernel-slim' image type. The 'full', 'beta' and 'beta-instanton' tags already include all features.
RUN features.sh

# Add an application
COPY --chown=1001:0  messaging.war /config/dropins/

# This script adds the requested server configuration, applies any interim fixes, and populates caches to optimize the runtime.
RUN configure.sh
