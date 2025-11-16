import { defHttp } from '/@/utils/http/axios';
import { AxiosRequestConfig } from 'axios';

enum Api {
  // 员工管理接口
  LIST = '/demo/hr/employee/list',
  QUERY_BY_ID = '/demo/hr/employee/queryById',
  ADD = '/demo/hr/employee/add',
  EDIT = '/demo/hr/employee/edit',
  DELETE = '/demo/hr/employee/delete',
  DELETE_BATCH = '/demo/hr/employee/deleteBatch',
  EXPORT_XLS = '/demo/hr/employee/exportXls',
  IMPORT_EXCEL = '/demo/hr/employee/importExcel',

  // 校验接口
  CHECK_EMPLOYEE_NO = '/demo/hr/employee/checkEmployeeNo',
  CHECK_ID_CARD = '/demo/hr/employee/checkIdCard',
  CHECK_MOBILE = '/demo/hr/employee/checkMobile',

  // 统计接口
  COUNT_BY_DEPARTMENT = '/demo/hr/employee/countByDepartment',
  COUNT_BY_STATUS = '/demo/hr/employee/countByStatus',
}

/**
 * 员工信息查询参数接口
 */
export interface EmployeeQueryParams {
  pageNo?: number;
  pageSize?: number;
  employeeNo?: string;
  name?: string;
  gender?: string;
  mobile?: string;
  email?: string;
  departmentId?: string;
  position?: string;
  employeeStatus?: string;
  education?: string;
  hireDate_begin?: string;
  hireDate_end?: string;
  column?: string;
  order?: 'asc' | 'desc';
}

/**
 * 员工信息接口
 */
export interface Employee {
  id?: string;
  employeeNo: string;
  name: string;
  gender?: string;
  birthDate?: string;
  idCard?: string;
  mobile?: string;
  email?: string;
  departmentId?: string;
  position?: string;
  hireDate?: string;
  employeeStatus?: string;
  address?: string;
  emergencyContact?: string;
  emergencyPhone?: string;
  education?: string;
  remark?: string;
  createBy?: string;
  createTime?: string;
  updateBy?: string;
  updateTime?: string;
  version?: number;
}

/**
 * 分页响应接口
 */
export interface PaginationResult<T> {
  records: T[];
  total: number;
  size: number;
  current: number;
  pages: number;
  optimizeCountSql: boolean;
  searchCount: boolean;
  countId: null;
}

/**
 * 响应结果接口
 */
export interface ApiResponse<T = any> {
  success: boolean;
  message: string;
  code: number;
  result: T;
  timestamp: number;
}

/**
 * 获取员工分页列表
 * @param params 查询参数
 * @returns 分页结果
 */
export const getEmployeeList = (params: EmployeeQueryParams) =>
  defHttp.get<PaginationResult<Employee>>({ url: Api.LIST, params });

/**
 * 根据ID查询员工详情
 * @param id 员工ID
 * @returns 员工详情
 */
export const getEmployeeById = (id: string) =>
  defHttp.get<Employee>({ url: Api.QUERY_BY_ID, params: { id } });

/**
 * 新增员工
 * @param data 员工信息
 * @returns 操作结果
 */
export const addEmployee = (data: Employee) =>
  defHttp.post<ApiResponse>({ url: Api.ADD, data });

/**
 * 编辑员工
 * @param data 员工信息
 * @returns 操作结果
 */
export const editEmployee = (data: Employee) =>
  defHttp.put<ApiResponse>({ url: Api.EDIT, data });

/**
 * 删除员工
 * @param id 员工ID
 * @returns 操作结果
 */
export const deleteEmployee = (id: string) =>
  defHttp.delete<ApiResponse>({ url: Api.DELETE + `?id=${id}` });

/**
 * 批量删除员工
 * @param ids 员工ID列表（逗号分隔）
 * @returns 操作结果
 */
export const deleteBatchEmployees = (ids: string) =>
  defHttp.delete<ApiResponse>({ url: Api.DELETE_BATCH + `?ids=${ids}` });

/**
 * 导出员工数据
 * @param params 查询参数
 * @param config 请求配置
 * @returns 文件流
 */
export const exportEmployeeData = (params?: EmployeeQueryParams, config?: AxiosRequestConfig) =>
  defHttp.get({ url: Api.EXPORT_XLS, params, responseType: 'blob' }, { isTransformResponse: false });

/**
 * 导入员工数据
 * @param data 表单数据（包含文件）
 * @returns 导入结果
 */
export const importEmployeeData = (data: FormData) =>
  defHttp.post<ApiResponse>({ url: Api.IMPORT_EXCEL, data, headers: { 'Content-Type': 'multipart/form-data' } });

/**
 * 校验员工工号唯一性
 * @param employeeNo 员工工号
 * @param id 员工ID（编辑时传入）
 * @returns 是否唯一
 */
export const checkEmployeeNoUnique = (employeeNo: string, id?: string) =>
  defHttp.get<boolean>({ url: Api.CHECK_EMPLOYEE_NO, params: { employeeNo, id } });

/**
 * 校验身份证号唯一性
 * @param idCard 身份证号
 * @param id 员工ID（编辑时传入）
 * @returns 是否唯一
 */
export const checkIdCardUnique = (idCard: string, id?: string) =>
  defHttp.get<boolean>({ url: Api.CHECK_ID_CARD, params: { idCard, id } });

/**
 * 校验手机号唯一性
 * @param mobile 手机号
 * @param id 员工ID（编辑时传入）
 * @returns 是否唯一
 */
export const checkMobileUnique = (mobile: string, id?: string) =>
  defHttp.get<boolean>({ url: Api.CHECK_MOBILE, params: { mobile, id } });

/**
 * 获取部门员工数量统计
 * @param departmentId 部门ID
 * @returns 员工数量
 */
export const getDepartmentEmployeeCount = (departmentId: string) =>
  defHttp.get<number>({ url: Api.COUNT_BY_DEPARTMENT, params: { departmentId } });

/**
 * 获取状态员工数量统计
 * @param employeeStatus 员工状态
 * @returns 员工数量
 */
export const getStatusEmployeeCount = (employeeStatus: string) =>
  defHttp.get<number>({ url: Api.COUNT_BY_STATUS, params: { employeeStatus } });