#!/usr/bin/env python3
import json
from datetime import datetime
file_path = '/opt/aws/amazon-cloudwatch-agent/etc/amazon-cloudwatch-agent.d/file_beanstalk.json'
with open(file_path, 'r') as f:
    current_time = datetime.now().strftime("%Y%m%d%H%M%S")
    jj = json.load(f)
    collect_list = jj['logs']['logs_collected']['files']['collect_list']
    one_entity = collect_list[0]
    one_entity_log_group_name = one_entity['log_group_name']
    path = '/'.join(one_entity_log_group_name.split('/')[:-1])
    new_collect_list = [
        {
            "file_path": "/var/app/current/logs/sulsul/info.log",
            "log_group_name": "/aws/elasticbeanstalk/sulsul-backend/application-info.log",
            "log_stream_name": "{instance_id}_" + current_time
        },
        {
            "file_path": "/var/app/current/logs/sulsul/debug.log",
            "log_group_name": "/aws/elasticbeanstalk/sulsul-backend/application-debug.log",
            "log_stream_name": "{instance_id}_" + current_time
        },
        {
            "file_path": "/var/app/current/logs/sulsul/error.log",
            "log_group_name": "/aws/elasticbeanstalk/sulsul-backend/application-error.log",
            "log_stream_name": "{instance_id}_" + current_time
        },
    ]
    collect_list += new_collect_list
    new_cloudwatch_logs = {
        'logs': {
            'logs_collected': {
                'files': {
                    'collect_list': collect_list
                }
            }
        }
    }
with open(file_path, 'w') as f:
    json.dump(new_cloudwatch_logs, f)
