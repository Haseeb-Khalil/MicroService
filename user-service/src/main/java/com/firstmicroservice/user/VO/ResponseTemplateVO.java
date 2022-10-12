package com.firstmicroservice.user.VO;

import com.firstmicroservice.user.entity.DepartmentUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private DepartmentUser departmentUser;
    private Department department;
}
