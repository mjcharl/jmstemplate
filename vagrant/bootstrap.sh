   #!/usr/bin/env bash
   
   sudo apt-get install wget ca-certificates
   wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
   sudo apt-get update
   sudo apt-get upgrade
   sudo apt-get install -y postgresql-9.3-postgis-2.1
   sudo -u postgres cp -f "/vagrant/pg_hba.conf" "/etc/postgresql/9.3/main"
   sudo -u postgres cp -f "/vagrant/postgresql.conf" "/etc/postgresql/9.3/main"
   sudo /etc/init.d/postgresql restart
   sudo -u postgres psql -U postgres -d postgres -c "create database mydb;"
   sudo -u postgres psql -U postgres -d postgres -c "alter user postgres with password 'postgres';"