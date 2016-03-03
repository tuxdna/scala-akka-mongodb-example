#!/bin/bash
# Always use exec according to best practices
# - http://www.projectatomic.io/docs/docker-image-author-guidance/

exec java -cp /app/server.jar in.tuxdna.services.StockAPI

