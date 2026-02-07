package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Dto.WorkloadResponse;

public interface WorkloadService {

    WorkloadResponse getUserWorkload(Long userId);
}
